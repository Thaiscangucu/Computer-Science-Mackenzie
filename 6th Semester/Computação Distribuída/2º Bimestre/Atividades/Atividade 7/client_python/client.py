import grpc
import os
import file_processor_pb2
import file_processor_pb2_grpc

def generate_requests(input_file_path, service_type, **kwargs):
    file_name = os.path.basename(input_file_path)
    
    with open(input_file_path, 'rb') as f:
        while True:
            chunk = f.read(1024)
            if not chunk:
                break
            
            request = file_processor_pb2.FileRequest(
                file_name=file_name,
                file_content=file_processor_pb2.FileChunk(content=chunk)
            )
            
            if service_type == 'compress_pdf':
                request.compress_pdf_params.CopyFrom(file_processor_pb2.CompressPDFRequest())
            elif service_type == 'convert_to_txt':
                request.convert_to_txt_params.CopyFrom(file_processor_pb2.ConvertToTXTRequest())
            elif service_type == 'convert_image':
                request.convert_image_format_params.CopyFrom(
                    file_processor_pb2.ConvertImageFormatRequest(output_format=kwargs['output_format'])
                )
            elif service_type == 'resize_image':
                request.resize_image_params.CopyFrom(
                    file_processor_pb2.ResizeImageRequest(width=kwargs['width'], height=kwargs['height'])
                )
            
            yield request

def execute_rpc_call(stub_method, input_file_path, output_file_path, service_type, **kwargs):
    print(f"Enviando {input_file_path} para o servidor...")
    try:
        request_iterator = generate_requests(input_file_path, service_type, **kwargs)
        response_stream = stub_method(request_iterator)
        
        with open(output_file_path, 'wb') as output_file:
            for response in response_stream:
                if not response.success and response.status_message:
                    print(f"Erro reportado pelo servidor: {response.status_message}")
                    return
                output_file.write(response.file_content.content)
                
        print(f"Sucesso! Arquivo salvo em: {output_file_path}")
    except grpc.RpcError as e:
        print(f"Erro gRPC: {e.details()}")
    except Exception as e:
        print(f"Erro local: {e}")

def run_client():
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = file_processor_pb2_grpc.FileProcessorServiceStub(channel)
        
        while True:
            print("\n=== Serviço de Processamento de Arquivos ===")
            print("1. Comprimir PDF")
            print("2. Converter PDF para TXT")
            print("3. Converter Formato de Imagem")
            print("4. Redimensionar Imagem")
            print("0. Sair")
            
            opcao = input("Escolha uma opção: ")
            
            if opcao == '0':
                break
                
            input_file = input("Digite o caminho do arquivo de entrada (ex: input.pdf ou foto.jpg): ")
            output_file = input("Digite o nome do arquivo de saída (ex: output.pdf ou foto.png): ")
            
            if not os.path.exists(input_file):
                print("Erro: Arquivo de entrada não encontrado!")
                continue

            if opcao == '1':
                execute_rpc_call(stub.CompressPDF, input_file, output_file, 'compress_pdf')
            
            elif opcao == '2':
                execute_rpc_call(stub.ConvertToTXT, input_file, output_file, 'convert_to_txt')
            
            elif opcao == '3':
                formato = input("Digite o formato de saída desejado (ex: png, jpg, webp): ")
                execute_rpc_call(stub.ConvertImageFormat, input_file, output_file, 'convert_image', output_format=formato)
            
            elif opcao == '4':
                largura = int(input("Digite a largura desejada (ex: 800): "))
                altura = int(input("Digite a altura desejada (ex: 600): "))
                execute_rpc_call(stub.ResizeImage, input_file, output_file, 'resize_image', width=largura, height=altura)
            else:
                print("Opção inválida.")

run_client()