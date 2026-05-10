import math
#a)
a = int(input("a: ))
b = int(input("b: "))
x = int(input("x: "))
y = (math.pow(a,2) + math.sqrt(3*b))/(5*math.pow(x,3))
print("y: ", y)

#b)
y = int(input("y: "))
a = int(input("a: "))
b = int(input("b: "))
x = (y + math.sqrt(2*b / a+b))
print('x: ',x)

#1) Leia dois números inteiros e exiba o quadrado da
#diferença do primeiro valor pelo segundo.

a = int(input("a: "))
b = int(input("b: "))
x = math.pow((a-b),2)
print('x: ')


#2) Receba o peso de uma pessoa, calcule e mostre:
#a) o novo peso, se a pessoa engordar 5% sobre o peso digitado;
#b) o novo peso, se a pessoa emagrecer 10% sobre o peso digitado.

peso = float(input("Peso: "))
a = peso + (peso*0.05)
b = peso - (peso*0.10)
print("engordou 5{x}: ".format(x="%"), "%.2f" %a)
print("emagreceu 10{x}: ".format(x="%"), "%.2f" %b)



#3) Leia um número inteiro de 3 dígitos, determine e
#apresente o número invertido (exemplo: número informado = 345,
#número apresentado = 543).


x = int(input('digite um número de 3 digitos: '))









