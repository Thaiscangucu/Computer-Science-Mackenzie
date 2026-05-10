#1. Faça um programa que leia dois números nas variáveis VAL1 e VAL2, calcule sua média na variável MEDIA e imprima seu valor.

VAL1 = float(input('digite um valor: '))
VAL2 = float(input('digite um valor: '))
media = (VAL1 + VAL2)/2

print('a média dos valores = ', media)


#2. Faça um programa que leia dois números nas variáveis NUM1 e NUM2, nessa ordem, e imprima em ordem inversa, isto é, se os dados lidos forem 5 e 9, por exemplo,
#devem ser impressos na ordem 9 e 5.

NUM1 = int(input('digite um valor: '))
NUM2 = int(input('digite um valor: '))
print (NUM2, NUM1)

#3. Faça um programa que leia o tempo de duração de um evento em uma fábrica, expressa em segundos, e mostre-o expresso em horas.
duracao = int(input('insira a duração em segundos: '))
h = (duracao/3600)
print('a duração em horas foi de ', h)

#4. Faça um programa que calcule a área de um triângulo, considerando a fórmula: área = (base*altura)/2. Utilize as variáveis
#AREA, BASE e ALTURA e os operadores aritméticos de multiplicação e divisão.
base = float(input("digite o valor da base cm: "))
altura = float(input("digite o valor da altura em cm: "))
area = (base*altura)/2
print('a area desse triângulo é: ', area, "cm^2")


#5. Faça um programa que:
#a) obtenha o valor para a variável AULAS (número de aulas que o professor ministra por mês);
aulas = int(input('digite a quantidade aulas dadas em 1 mês: '))
print('o professor ministra ', aulas, 'por mês.')

#b) obtenha o valor para a variável VALOR (valor, em reais, de cada aula que o professor ministra);
valor = int(input('digite o valor de 1 aula: '))
print('o valor de 1 aula é de: ', valor, 'reais')

#d) calcule e mostre o SALARIO (salário bruto do professor), que é referente ao número de aulas que ele ministrou no mês (AULAS * VALOR).
salario = (aulas*valor)
print("o salário desse professor é de: ", salario, 'reais')


