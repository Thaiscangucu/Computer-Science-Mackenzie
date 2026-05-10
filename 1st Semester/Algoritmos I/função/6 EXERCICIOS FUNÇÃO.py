#thais ferreira canguçu
#TIA - 32270402

def main():
    n1 = int(input("n1 para soma: "))
    n2 = int(input("n2 para soma: "))
    resultadoSoma = soma(n1, n2)
    print("resultado soma: ", resultadoSoma)
    
    num = int(input("número fatorial: "))
    print(num, "! =", calculaFatorial(num))

    y = int(input("Insira um número para verificar se é postivo ou negativo: "))
    y = argumento(y)

    numero1 = int(input("n1: "))
    numero2 = int(input("n2: "))
    maiorN(numero1, numero2)
    
    a = int(input("n1 para potência: "))
    b = int(input("n2 para a potência: "))
    x = pot(a,b)
    print(a, "elevado a ", b, "é:", pot(a,b))
    
    valor = int(input("Insira o valor da conta: "))
    gorjeta = valor*0.1
    print("O valor da gorjeta a ser pago é: ", gorjeta)

#Ex1 Faça um programa que receba dois números, soma e mostra o resultado da soma. Crie a função main()
#para receber as entradas e a função soma para calcular a soma.

def soma(n1,n2):
    soma = n1 + n2
    return soma

    
#Ex2 Faça um programa que recebe um número do usuário, calcula e mostra o fatorial desse número. Crie a função calculaFatorial

def calculaFatorial (num):
    if num < 0:
        print("invalido")
    elif num == 0:
        return 1
    else:
        fact = 1
        while num > 1:
            fact *= num
            num -= 1
        return fact
    
#Ex3 Faça um programa, com uma função que necessite de um argumento. A função retorna o valor de
#caractere ‘P’, se seu argumento for positivo, e ‘N’, se seu argumento for zero ou negativo.
def argumento(y):
    if y >= 0:
        print("p")
    else:
        print("n")

#4. Faça um programa que receba dois números inteiros do usuário n1 e n2,
#de odo que n1 < n2. Crie uma função que imprime os números de
#n1 a n2 na ordem inversa.

def maiorN(numero1, numero2):
    for i in range(numero2,numero1-1,-1):
        print(i)

#Ex5. Faça uma função que computa a potência ab para valores a e b
#(assuma números inteiros) passados por parâmetro (não use o operador **).

import math
def pot (a,b):
    pot = math.pow(a,b)
    return pot

#Ex6. Escreva uma função que, dado o valor da conta de um restaurante,
#calcule e exiba a gorjeta do garçom,considerando 10% do valor da conta.

def gorjeta(valor):
    gorjeta = valor-(valor*0.1)
    return gorjeta

main()

