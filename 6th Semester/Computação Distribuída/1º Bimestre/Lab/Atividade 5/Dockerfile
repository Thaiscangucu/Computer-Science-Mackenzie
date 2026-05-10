FROM python:3.10-slim

# Garante privilégios de root para o build
USER root

# Instala dependências
RUN apt-get update && apt-get install -y \
    openssh-server \
    openssh-client \
    libopenmpi-dev \
    openmpi-bin \
    build-essential \
    && rm -rf /var/lib/apt/lists/*

# Configura Python
RUN pip install --no-cache-dir mpi4py numpy

# Configura SSH de forma robusta
RUN mkdir -p /var/run/sshd && \
    echo 'root:mpi' | chpasswd && \
    sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config && \
    sed -i 's/StrictHostKeyChecking ask/StrictHostKeyChecking no/' /etc/ssh/ssh_config

# Gera chaves SSH
RUN ssh-keygen -t rsa -f /root/.ssh/id_rsa -N "" && \
    cat /root/.ssh/id_rsa.pub >> /root/.ssh/authorized_keys && \
    chmod 600 /root/.ssh/authorized_keys

WORKDIR /app
COPY analise_pulmonar_mpi.py .

EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]