#Ex1 Faça um programa que receba dois números, soma e mostra o resultado da soma. Crie a função main()
#para receber as entradas e a função soma para calcular a soma.

def soma(n1,n2):
    soma = n1 + n2
    return soma

def main():
    n1 = int(input())
    n2 = int(input())

    resultadoSoma = soma(n1, n2)
    print("resultado soma: ", resultadoSoma)
main()

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
num = int(input())

print("fatorial de", num, "é", calculaFatorial(num))
            
    
