# Lab gRPC: Sistema Distribuído de Processamento de Arquivos
Aluno: Thais Ferreira Canguçu - 10403283
Aluno: Miguel Piñeiro Coratolo Simões - 10427085

Solução para a atividade de Laboratório de gRPC. Sistema cliente-servidor construído com **gRPC** e **Protocol Buffers**, que permite ao cliente enviar arquivos para serem processados remotamente pelo servidor utilizando streaming bidirecional.

## Tecnologias Utilizadas
* **Backend (Servidor):** C++17, gRPC, Protobuf.
* **Frontend (Cliente):** Python 3, gRPC.
* **Infraestrutura:** Docker (Ubuntu 22.04).
* **Ferramentas de Processamento:** Ghostscript (`gs`), Poppler (`pdftotext`), ImageMagick (`convert`).

## Instruções para compilação
1. **Construir a imagem Docker:**
   Isso instalará o Ubuntu, os compiladores C++ nativos e as ferramentas de imagem/PDF.
   ```bash
   docker build -t lab-grpc-server .

2. **Rodar o Container:**
    ```bash
    docker run -p 50051:50051 --name meu-servidor-grpc --rm lab-grpc-server

3. **Rodar Cliente:**
    Em uma nova janela de terminal, navegue até a pasta do cliente e execute o script:
    ```bash  
    cd client_python
    python3 client.py