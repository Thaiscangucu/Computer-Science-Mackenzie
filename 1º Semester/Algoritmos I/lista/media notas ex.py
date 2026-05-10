import random

def media(alunos):
  lista = []
  aprovados = 0
  for i in range(alunos):
    n1 = random.randint(1,10)
    n2 = random.randint(1,10)
    n3 = random.randint(1,10)
    n4 = random.randint(1,10)
    media = (n1 + n2 + n3 + n4)/4
    lista.append(media)
    if media >= 7:
      aprovados =+ 1
  print(lista)
  print(aprovados, "aluno(s) aprovado(s)")

def main():
  alunos = int(input("Digite o número de alunos "))
  media(alunos)
  
main()
