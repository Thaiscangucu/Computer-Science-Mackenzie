#Thais Ferreira Canguçu - 32270402


#A fábrica de refrigerantes Meia-Cola vende seu produto em três formatos:
#lata de 350 ml, garrafa de 600 ml e garrafa de 2 litros. Um comerciante compra uma determinada
#quantidade de cada formato. Desenvolva um programa que solicite a quantidade de itens que ele comprou
#de cada formato e imprima na tela quantos litros de refrigerante ele comprou no total.

L = float(input('Insira a quantidade de latas: '))
G = float(input('Insira a quantidade de garrafas de 600ml: '))
G2 = float(input('Insira a quantidade de garrafas de 2L: '))

total = (L*0.35) + (G*0.6) + (G2*2)
print('Total de Litros: ', total, "L")
