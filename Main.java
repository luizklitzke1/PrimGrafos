//Alunos: Arthur B. Pinotti & Luiz G. Klitzke

import java.util.Scanner;

public class Main 
{
    //Busca a próxima menor vértice do grafo a ser processada
    public static int GetMenorChave(int chavesDisponiveis[], boolean visitadas[])
    {
        int menorIdx = 0;
        int menor = Integer.MAX_VALUE;
 
        for (int i = 0; i < chavesDisponiveis.length; ++i)
        {
            if (visitadas[i] == true)
                continue; // Já foi processada

            if (chavesDisponiveis[i] < menor)
            {
                menor = chavesDisponiveis[i];
                menorIdx = i;
            }
        }
   
        return menorIdx;
    }

	public static void main (String[] args) throws java.lang.Exception
	{
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(); // numero de cidades
        int m = scan.nextInt(); // numero de rodovias

        int  [][] adjacencia = new int [n][n];
        boolean[] visitadas  = new boolean[n];
        int    [] chaves     = new int    [n];

        //Inicializar os vetores de adj, chaves e visitadas
        for (int i = 0; i < n; ++i)
        {
            visitadas[i] = false;
            chaves   [i] = Integer.MAX_VALUE;

            for (int j = 0; j < n; ++j)
            {
                adjacencia[i][j] = 0;
            }
        }
        
        for (int i = 0; i < m; ++i) // Input das rodovias pra matriz de adj
        {
            int u = scan.nextInt(); // cidade A
            int v = scan.nextInt(); // cidade B
            int c = scan.nextInt(); // dist

            adjacencia[u - 1][v - 1] = c;
            adjacencia[v - 1][u - 1] = c;
        }
	
        chaves[0] = 0; // Pegamos a primeira como partida arbitraria

        for (int idxIteracao = 0; idxIteracao < n - 1; ++idxIteracao)
        {
            int vert = GetMenorChave(chaves, visitadas);
            visitadas[vert] = true;

            for (int idxAdj = 0; idxAdj < n; ++idxAdj)
            {
                if (visitadas[idxAdj] == true)
                    continue;
                    
                int distancia = adjacencia[vert][idxAdj];

                if (distancia == 0)
                    continue; // nao tem adjancencia

                if (distancia < chaves[idxAdj])
                    chaves[idxAdj] = distancia;
            }   
        }

        int comprimento = 0;

        for (int i = 0; i < n; ++i)
        {
            comprimento += chaves[i];
        }
        
        System.out.print(comprimento);

        scan.close();
    }
}
