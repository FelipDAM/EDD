#Codi de Felip Lloret!
#Vectors iniciats
noms = []
preus = []
qvendes = []
repetirprograma = True

#Inici programa
while(repetirprograma):
    print("1. Introduir un article nou")
    print("2. Fer una venda")
    print("3. Mostrar informació")
    print("4. Esborrar un article")
    print("5. Esborrar tots els articles")
    print("6. Eixir")
    
    num = int(input("Quina opció vols polsar?"))
    while(num<1 or num>6):
        num = int(input("Quina opció vols polsar?"))
    
    #Afegim un nou Article amb el preu i quantitat corresponent  
    if(num == 1):
        article = str(input("Nom de l'article nou"))
        noms.append(article)
        article = float(input("Preu unitari de l'article"))
        preus.append(article) 
        article = int(input("Quantitat de ventes"))
        qvendes.append(article)
        
    #Afegim quantitats venudes a un article        
    elif(num == 2):
        article = input("Nom de l'article: ")
        if article in noms:
            articlebuscat = noms.index(article)
            article = int(input("Quantitat de ventes a introduir "))
            qvendes[articlebuscat] += article
            print(f"Ventes de {article} actualizadas a {qvendes[articlebuscat]} unidades.")
        else:
            print("Article inexistent")
            
    #Mostra l'informació dels Articles,preu,quantitat de vendes       
    elif(num == 3):
        maxventa = 0
        nommaxventa = ""
        nommaxingres = ""
        ventatotal = 0
        maxingresos = 0
        
        invers = str(input("Vols vore la llista en ordre invers? S/N"))
        
        for i in range(len(qvendes)):
            ventatotal+=qvendes[i]*preus[i]
            
            if(qvendes[i]*preus[i]>maxingresos):
                maxingresos=qvendes[i]*preus[i]
                nommaxingres = noms[i]
                
            if(qvendes[i]>maxventa):
                maxventa=qvendes[i]
                nommaxventa=noms[i]
           
                        
        while invers not in ["S", "N", "s", "n"]:
            invers = str(input("MAL S/N"))
        #Taula
        print(f"{'NOMS':>10}{'QUANT':>10}{'PREUS':>10}{'IMPORT':>10}")
        print(f"{'----':>10}{'-----':>10}{'-----':>10}{'------':>10}")
        
            
        if(invers == "S" or invers == "s"):
            for i in range(len(noms)-1,-1,-1):
                print(f"{noms[i]:>10}{qvendes[i]:>10}{preus[i]:>10.2f}{qvendes[i]*preus[i]:>9.2f}€")  
        else:
            for i in range(0,len(noms)):
                print(f"{noms[i]:>10}{qvendes[i]:>10}{preus[i]:>10.2f}{qvendes[i]*preus[i]:>9.2f}€")
                
        print("")   
        print(f"{'':>20}{'TOTAL:':>10}{ventatotal:>9.2f}€")
        print("")       
        print(f"L'objecte més venut ha sigut el {nommaxventa} ha venut {maxventa} unitats")       
        print(f"L'article amb més ingressos ha segut {nommaxingres} amb {maxingresos:.2f}€")    
        
    #Borrar l'article que vullgam   
    elif(num == 4):
        article = input("Nom de l'article a esborrar: ")
        if article in noms:
            articlebuscat = noms.index(article)
            noms.pop(articlebuscat)
            qvendes.pop(articlebuscat)
            preus.pop(articlebuscat)
        else:
            print("Article inexistent")
   
    #Buidar els vectors       
    elif(num == 5):
        noms = []
        preus = []
        qvendes = []
        
    #Eixir del programa S/N    
    else:
       acabar = str(input("Segur que vols eixir S/N"))
       while acabar not in ["S", "N", "s", "n"]:
            acabar = str(input("MAL S/N"))
            
       if(acabar=="S" or acabar=="s"):
           repetirprograma=False
       else:
           repetirprograma
           
        
          