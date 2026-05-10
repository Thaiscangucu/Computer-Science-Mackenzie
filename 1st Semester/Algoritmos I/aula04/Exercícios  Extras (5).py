#Thais Ferreira Canguçu - 32270402


##Três amigos, Carlos, André e Felipe, decidiram rachar igualmente a conta em um bar. Faça um programa
#para ler o valor total da conta e imprimir quanto cada um deve pagar, mas faça com que Carlos e André não
#paguem centavos. Por exemplo: uma conta de R$101,53 resulta em R$33,00 para Carlos, R$33,00 para André e R$ 35,53 para Felipe.

C = int(input('quanto Carlos gastou: '))
A = int(input('quanto André gastou:'))
F = int(input('quanto Felipe gastou: '))

total = (C + A + F)
print("total: ", total)
print('Carlos: R$', int(total/3), 'André: R$', int(total/3), 'Felipe: R$', float(total/3))
