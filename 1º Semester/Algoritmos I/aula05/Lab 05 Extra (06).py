#Thais Ferreira Canguçu - 32270402


#Determine os resultados obtidos na avaliação das expressões lógicas seguintes sabendo que a, b, c contém,
#respectivamente, 2, 7, 3.5, e que existem duas variáveis m e n cujos conteúdos são, respectivamente,
#False e True:
#a) b == a * c and (m or n)
#b) b > a or b == math.pow(a,a)
#c) m and b // a >= c or not a <= c
#d) not m or n and math.sqrt(a + b) >= c
#e) b/a == c or b/a != c
#f) m or math.pow(b,a) <= c * 10 + a * b
import math
a = 2
b = 7
c = 3.5
m = False
n = True

#a
print ('a) ',b == a * c and (m or n))

#b
print ("b) ", b > a or b == math.pow(a,a))

#c
print('c) ', m and b // a >= c or not a <= c)

#d
print('d) ', not m or n and math.sqrt(a + b) >= c)

#e
print('e) ', b/a == c or b/a != c)

#f
print('f) ', m or math.pow(b,a) <= c * 10 + a * b)
