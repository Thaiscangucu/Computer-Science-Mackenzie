print("M/m - matutino, V/v - vespertino e N/n - noturno")
turno = input('Em que turno você estuda? ')
if turno == "M" or turno == "m":
    print('Bom dia! ')
elif turno == "V" or turno == "v":
    print('Boa tarde!')
elif turno == "N" or turno == "n":
    print('Boa noite! ')
else:
    print("Válor inválido!")
