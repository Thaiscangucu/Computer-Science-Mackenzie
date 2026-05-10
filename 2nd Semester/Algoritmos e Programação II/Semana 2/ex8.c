#include <stdio.h>

void inter(int a[], int b[], int n, int m){
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      if(a[i]== b[j]){
        printf( "%d ", a[i]);
      }
    }
  }
}

int main() {
    int a[] = {0,3,8,2,5,1};
    int b[] = {4,7,10,3,2};
    int n = 6;
    int m = 5;
    int i;
    for(i=0; i<n; i++){
      printf("%d", a[i]);
    }
    int j;
    printf("/n");
    for(j=0; j<m; j++){
      printf("%d ", b[j]);
    }
    inter(a,b,n,m);
    return 0;
}