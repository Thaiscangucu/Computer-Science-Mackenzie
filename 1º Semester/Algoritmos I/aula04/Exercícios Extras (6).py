#Thais Ferreira Canguçu - 32270402


#Elabore um programa que calcular sua idade aproximada em segundos. Para tanto, considere que
#programa deve trabalhar com a data de 1º. de janeiro de 1990 até a data presente. Para tanto, considere:
#** mês (1-12) 

#** dia (1-31) 

#** ano (4 dígitos)

d = int(input('Digite o dia de nascimento: '))
m = int(input('Digite o mês de nascimento (em números ex:01): '))
a = int(input('Digite o ano de nascimento (em 4 digitos): '))
idade = (2022-a)
print("idade", idade, 'anos')
segundos = (idade*31104000)
print('Sua idade em segundos aproximadamente: ', segundos, 's')
