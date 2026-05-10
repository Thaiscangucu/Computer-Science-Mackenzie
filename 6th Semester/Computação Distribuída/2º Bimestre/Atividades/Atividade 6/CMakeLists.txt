# Versão mínima do CMake e nome do projeto [cite: 83, 84]
cmake_minimum_required(VERSION 3.10)
project(grpc_lab CXX)

# Configuração do padrão C++17 [cite: 85, 86]
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++17")

# Encontra os pacotes de biblioteca necessários [cite: 87, 88, 89]
find_package(gRPC REQUIRED)
find_package(Protobuf REQUIRED)

# Seção de Geração de Código Explícita [cite: 91]
# Encontra os executáveis de compilação (protoc e plugin gRPC) [cite: 92, 93, 94, 100]
find_program(PROTOC_EXECUTABLE protoc)
find_program(GRPC_CPP_PLUGIN grpc_cpp_plugin)

# Verifica se os executáveis foram encontrados [cite: 95, 97, 101, 102]
if(NOT PROTOC_EXECUTABLE)
    message(FATAL_ERROR "Compilador 'protoc' não encontrado.")
endif()

if(NOT GRPC_CPP_PLUGIN)
    message(FATAL_ERROR "Plugin 'grpc_cpp_plugin' não encontrado.")
endif()

# Define os nomes dos arquivos que serão gerados a partir de notas.proto [cite: 103, 104, 105, 106, 107]
set(PROTO_SRC_FILES ${CMAKE_CURRENT_BINARY_DIR}/notas.pb.cc)
set(PROTO_HDR_FILES ${CMAKE_CURRENT_BINARY_DIR}/notas.pb.h)
set(GRPC_SRC_FILES ${CMAKE_CURRENT_BINARY_DIR}/notas.grpc.pb.cc)
set(GRPC_HDR_FILES ${CMAKE_CURRENT_BINARY_DIR}/notas.grpc.pb.h)
set(GRPC_CPP_PLUGIN "/opt/homebrew/bin/grpc_cpp_plugin")

# Define o comando customizado para gerar os arquivos a partir do .proto [cite: 108, 110, 118, 119]
add_custom_command(
    OUTPUT ${PROTO_SRC_FILES} ${PROTO_HDR_FILES} ${GRPC_SRC_FILES} ${GRPC_HDR_FILES}
    # Comando para gerar mensagens [cite: 110, 111, 112, 113]
    COMMAND ${PROTOC_EXECUTABLE} --cpp_out=${CMAKE_CURRENT_BINARY_DIR} -I${CMAKE_CURRENT_SOURCE_DIR} ${CMAKE_CURRENT_SOURCE_DIR}/notas.proto
    # Comando para gerar serviços gRPC [cite: 114, 115, 116, 117]
    COMMAND ${PROTOC_EXECUTABLE} 
            --grpc_out=${CMAKE_CURRENT_BINARY_DIR} 
            --plugin=protoc-gen-grpc=${GRPC_CPP_PLUGIN} 
            -I${CMAKE_CURRENT_SOURCE_DIR} 
            ${CMAKE_CURRENT_SOURCE_DIR}/notas.proto
    DEPENDS ${CMAKE_CURRENT_SOURCE_DIR}/notas.proto
    COMMENT "Gerando código a partir de notas.proto"
)
# Comando para gerar serviços gRPC


# Cria um alvo para garantir a geração de código [cite: 120, 121]
add_custom_target(generate_grpc_files ALL DEPENDS ${PROTO_SRC_FILES} ${PROTO_HDR_FILES} ${GRPC_SRC_FILES} ${GRPC_HDR_FILES})

# Adiciona o diretório dos cabeçalhos gerados ao include path [cite: 125]
include_directories(${CMAKE_CURRENT_BINARY_DIR})

# Configuração do executável do SERVIDOR [cite: 126, 128]
add_executable(servidor_cpp servidor.cpp ${PROTO_SRC_FILES} ${GRPC_SRC_FILES})
target_link_libraries(servidor_cpp PRIVATE protobuf::libprotobuf gRPC::grpc++)

# Configuração do executável do CLIENTE [cite: 129, 130]
add_executable(cliente_cpp cliente.cpp ${PROTO_SRC_FILES} ${GRPC_SRC_FILES})
target_link_libraries(cliente_cpp PRIVATE protobuf::libprotobuf gRPC::grpc++)
