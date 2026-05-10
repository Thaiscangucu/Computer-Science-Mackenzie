cmake_minimum_required(VERSION 3.15)
project(FileProcessorServer)

set(CMAKE_CXX_STANDARD 17)

find_package(gRPC CONFIG REQUIRED)
find_package(Protobuf REQUIRED)

add_executable(server 
    server.cc 
    file_processor.pb.cc 
    file_processor.grpc.pb.cc
)

target_include_directories(server PRIVATE ${CMAKE_CURRENT_SOURCE_DIR})

target_link_libraries(server 
    gRPC::grpc++ 
    gRPC::grpc++_reflection
    protobuf::libprotobuf
)