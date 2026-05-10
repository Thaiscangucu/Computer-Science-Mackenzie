#Nome: Thais Ferreira Canguçu  RA: 10403283
#Ajuste de Retas

def obter_pontos():
    pontos = []
    for i in range(4):
        x = float(input(f"Digite a coordenada x do ponto {i+1}: "))
        y = float(input(f"Digite a coordenada y do ponto {i+1}: "))
        pontos.append((x, y))
    return pontos

def minimos_quadrados(pontos):
    n = len(pontos)
    soma_x = 0
    soma_y = 0
    soma_x2 = 0
    soma_xy = 0

    for i in range(n):
        x = pontos[i][0]
        y = pontos[i][1]
        soma_x += x
        soma_y += y
        soma_x2 += x * x
        soma_xy += x * y

    denominador = n * soma_x2 - soma_x ** 2

    a = (n * soma_xy - soma_x * soma_y) / denominador
    b = (soma_y - a * soma_x) / n

    return a, b


pontos = obter_pontos()
a, b = minimos_quadrados(pontos)
print(f"Coeficientes da reta ajustada: a = {a:.4f}, b = {b:.4f}")
