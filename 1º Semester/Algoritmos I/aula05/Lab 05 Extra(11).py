# Thais Ferreira Canguçu - 32270402


#Um pescador comprou um computador para controlar o rendimento diário de seu trabalho.
#Toda vez que ele traz um peso de peixes maior que o estabelecido pelo regulamento de pesca do Estado de São Paulo (50 quilos),
#deve pagar uma multa de R$ 4,00 por quilo excedente. Escreva um programa que leia o peso de peixes,
#e verifique se há excesso. Se houver, determine o peso excedente e o valor da multa.Caso contrário, mostrar “Dentro do regulamento”.
peso = float(input('insira o peso do peixe: '))
if peso > 50:
    print("valor a ser pago: ", "R$",(peso - 50)*4)
else:
    print("Dentro do regulamento")
