
--  GRUPO:
--    Nome: Miguel Piñeiro Coratolo Simões RA: 10427085
--    Nome: Bruno Germanetti Ramalho RA: 10426491
--    Nome: Thais Ferreira Canguçu RA: 10403283


-- Contagem da frequência de caracteres
    rle [] = []
    rle (c:xs) = rl xs c 1 
        
    rl [] c n = [(c,n)]
    rl (a:xs) c n 
        | a == c    = rl xs c (n+1)
        | otherwise = (c,n) : rl xs a 1
    
-- Construção da árvore
    data Arvore = No Integer Arvore Arvore | Folha (Char, Integer)
        deriving (Show, Eq) 
        
    freq (No f _ _)     = f
    freq (Folha (_, f)) = f
    
    insereOrdenadoPorFreq no [] = [no]
    insereOrdenadoPorFreq no (n:ns)
        | freq no <= freq n = no : n : ns
        | otherwise         = n : insereOrdenadoPorFreq no ns
    
-- Insertion sort
    ordenaInicial [] = []
    ordenaInicial (n:ns) = insereOrdenadoPorFreq n (ordenaInicial ns)
    
-- Transforma lista de pares em uma lista de Folhas (map manual)
    folhaP [] = []
    folhaP (par:pares) = Folha par : folhaP pares
    
-- Constrói a árvore, a partir das frequências
    constroiArvore [sozinho] = No (snd sozinho) (Folha sozinho) (Folha sozinho) 
    constroiArvore freqList =
        let folhas = folhaP freqList
            listaOrdenada = ordenaInicial folhas
        in combinaNos listaOrdenada
    
    combinaNos [raiz] = raiz
    combinaNos (n1:n2:ns) =
        let novoNo = No (freq n1 + freq n2) n1 n2
        in combinaNos (insereOrdenadoPorFreq novoNo ns)
    
-- Tabela de códigos 
    geraCodigos (Folha(c, _)) = [(c, "0")] 
    geraCodigos arvore = percorre arvore ""
      where
        percorre (Folha (c, _)) caminho = [(c, caminho)]
        percorre (No _ esq dir) caminho =
            percorre esq (caminho ++ "0") ++ percorre dir (caminho ++ "1")
    
-- Codifica o texto
    lst _ [] = []
    lst f (x:xs) = f x : lst f xs
    unir [] = ""
    unir (s:ss) = s ++ unir ss
     
    buscaCodigo char ((c, codigo):resto)
        | char == c = codigo
        | otherwise = buscaCodigo char resto
    codificaTexto texto tabela = unir (lst (\c -> buscaCodigo c tabela) texto)
    
    
-- =================================================================
    
    
    main :: IO ()
    main = do
        conteudo <- readFile "in.txt"
        
        let frequencia = rle conteudo
        putStrLn "\nFrequência de cada palavra:"
        print frequencia
        
        let arvore = constroiArvore frequencia
        putStrLn "\nÁrvore:"
        print arvore
        
        let tabelaDeCodigos = geraCodigos arvore
        putStrLn "\nTabela de códigos:"
        print tabelaDeCodigos
    
        
        let textoCodificado = codificaTexto conteudo tabelaDeCodigos
        putStrLn "\nCodificação:"
        print textoCodificado
    
        
        writeFile "out.txt" textoCodificado
