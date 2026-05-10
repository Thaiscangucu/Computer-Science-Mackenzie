
#1. Faça um programa que leia 4 notas bimestrais de uma disciplina,
#calcule e mostre a média aritmética do bimestre.


N1 = float(input('digite sua nota: '))
N2 = float(input('digite sua nota: '))
N3 = float(input('digite sua nota: '))
N4 = float(input('digite sua nota: '))
media = (N1 + N2 + N3 + N4)/4
print("média: ", media)


#2. Faça um programa que converta metros para milímetros (1m = 1000mm).

Metros = float(input("quantidade em metros: "))
Milimetros = (Metros * 1000)
print("Milímetros: ", Milimetros, "mm")

#3. Faça um programa que calcule a área de um quadrado, em seguida mostre o dobro desta área para o
#usuário (area = lado*lado).


lado = float(input('lado: '))
area = (lado*lado)
dobro = (area*2)
print("area: ", area)
print('dobro da area: ', dobro)

#4. Faça um programa que pergunte quanto você ganha por hora e o número de
#horas trabalhadas no mês. Calcule e mostre o total do seu salário
#no referido mês.

print("Quanto você ganha por hora? ")
S = float(input("digite quanto voce ganha por hora: "))
M =(S*720)
print("voce ganha ", S , "reais por hora")
print("voce ganha ", M , "reais em 1 mes")


#5. Faça um programa que calcule e mostre o IMC (índice de massa corporal)de
#uma pessoa, considerando que ela irá dizer qual o seu peso e
#qual a sua altura ( imc = peso/(altura*altura) ).

peso = float(input("Digite seu peso: "))
altura = float(input("Digite sua altura: "))
imc = peso/(altura*altura)
print("seu imc é de: ", imc)
