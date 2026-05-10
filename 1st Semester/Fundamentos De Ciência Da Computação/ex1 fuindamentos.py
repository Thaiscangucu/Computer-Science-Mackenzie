num = int(input("insira o numero a ser convertido: "))
base = int(input("digite a base do numero digitado: "))

if base == 2:
    decimal = 0 #irá representar a soma de cada base do decimal 
    expoente = 0 #fara a contagem de cada casa do numero binario
    while num>=1:
        if num%10==1:
            decimal = decimal + 2**(expoente)
            num = (num - (num%10))/10 #irá pular uma casa para a direita para ser lida 
            expoente = expoente + 1
            print ("O decimal é ",decimal)
