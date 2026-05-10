#!/bin/bash

# Constrói a imagem (Dockerfile)
docker compose build

# Sobe os containers em segundo plano (-d)
docker compose up -d

echo "Cluster iniciado com sucesso!"
