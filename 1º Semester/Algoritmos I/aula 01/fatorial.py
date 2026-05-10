#5! = 5*4*3*2*1
#n! = n*(n−1)*(n−2)*. . . 1 e 0! = 1

def fatorial(n):
   fat = 1
   cont = n
   while cont > 1:      
      fat *= cont   # o mesmo que fat = fat * cont
      cont -= 1       # o mesmo que cont = cont + 1

   return fat

# testes
print("0! =", fatorial(0))
print("1! =", fatorial(1))
print("5! =", fatorial(5))
print("9! =", fatorial(9))
