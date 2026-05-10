#exercício números pares-ímpares-primos
#thais ferreira canguçu
#32270402

import random
pares = 0
impares = 0
primos = 0
contador = 0
totalDivisores = 0
while contador < 10:
    num = random.randint(1,100)
    if num % 2 == 0:
        pares +=1
    else:
        impares +=1
    contadorPrimo = num-1
    while contadorPrimo > 2:
        if num % contadorPrimo == 0:
            totalDivisores +=1
        contadorPrimo -=1
    if totalDivisores == 0 and num != 1:
        primos +=1
    contador +=1
print("pares:", pares)
print("impares:", impares)
print("primos:", primos)


