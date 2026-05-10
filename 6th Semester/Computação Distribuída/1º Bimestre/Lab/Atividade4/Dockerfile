FROM ubuntu:22.04

# Evita que o apt peça interações durante a instalação
ENV DEBIAN_FRONTEND=noninteractive

# Instala todas as dependências em um único comando RUN
RUN apt update && \
    apt install -y \
    openmpi-bin \
    libopenmpi-dev \
    openssh-server \
    sudo \
    python3 \
    python3-pip \
    figlet \
    net-tools \
    iputils-ping && \
    # Instala a biblioteca MPI para Python
    pip3 install mpi4py && \
    # Cria o usuário mpiuser e configura permissões
    useradd -m -s /bin/bash mpiuser && \
    echo "mpiuser:mpi123" | chpasswd && \
    adduser mpiuser sudo && \
    # Cria a pasta necessária para o serviço SSH
    mkdir /var/run/sshd

# Expõe a porta do SSH
EXPOSE 22

# Inicia o serviço e mantém o container rodando
CMD service ssh start && sleep infinity