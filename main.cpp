#ifdef _MSC_VER
#define _CRT_SECURE_NO_WARNINGS
#endif

//Alunos: Arthur B. Pinotti & Luiz G. Klitzke

#include <stdio.h>

using namespace std;

int n, m, u, v, c;

#define INT_MAX 2147483647

int GetMenorChave(int chavesDisponiveis[], bool visitadas[])
{
    int menorIdx = -1;
    int menor = INT_MAX;

    for (int i = 0; i < n; ++i)
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

int main()
{
    scanf("%d %d", &n, &m);

    int adjacencia[500][500];
    bool visitadas[500];
    int chaves[500];

    //Inicializar os vetores de adj, chaves e visitadas
    for (int i = 0; i < n; ++i)
    {
        visitadas[i] = false;
        chaves[i] = INT_MAX;

        for (int j = 0; j < n; ++j)
        {
            adjacencia[i][j] = 0;
        }
    }

    for (int i = 0; i < m; ++i) // Input das rodovias pra matriz de adj
    {
        scanf("%d %d %d", &u, &v, &c);

        adjacencia[u - 1][v - 1] = c;
        adjacencia[v - 1][u - 1] = c;
    }

    chaves[0] = 0; // Pegamos a primeira como partida arbitraria

    for (int idxIteracao = 0; idxIteracao < n; ++idxIteracao)
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

    printf("%d\n", comprimento);

    return 0;
}