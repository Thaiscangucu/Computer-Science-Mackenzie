import random
lista = []

def geraLista(n):
    for i in range(n):
        lista.append(random.randint(1,100))

def elemento(x):
    if x in lista:
        print(x, "está na lista")
    else:
        print(x, "não está na lista")
    
def main():
    n = int(input("quantidade de elementos: "))
    geraLista(n)
    print("lista: ", lista)

    lista.sort()
    print("ordem crescente: ", lista)

    x = int(input("número: "))
    elemento(x)
    
   
    y = len(lista)
    print("tamanho da lista: ", y)

main()









