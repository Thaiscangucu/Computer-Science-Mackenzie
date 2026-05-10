#Thais Ferreira Canguçu - 32270402

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
