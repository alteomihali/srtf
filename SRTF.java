import java.io.*;

public class SRTF {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n;
		System.out.println("Vendosni numrin e Proceseve: ");
		n = Integer.parseInt(br.readLine());
		int pr[][] = new int[n + 1][4];
      
		for(int i = 1; i <= n; i++) {
			System.out.println("Vendosni Kohen e Mberritjes se Procesit " + i + ": ");
			pr[i][0] = Integer.parseInt(br.readLine());
			System.out.println("Vendosni Burst Time te Procesit " + i + ": ");
			pr[i][1] = Integer.parseInt(br.readLine());
		}
			System.out.println();
     
			int total_time = 0;
			for(int i = 1; i <= n; i++) {
				total_time += pr[i][1];
			}
			int t_ch[] = new int[total_time];
     
			for(int i = 0; i < total_time; i++){
				int selected_pr = 0;
				int min = 99999;
				for(int j = 1; j <= n; j++){
					if(pr[j][0] <= i) {
						if(pr[j][1] < min && pr[j][1] != 0) {
							min = pr[j][1];
							selected_pr = j;
						}
					}
				}
				t_ch[i] = selected_pr;
				
				pr[selected_pr][1]--;
      
				for(int j = 1; j <= n; j++) {
					if(pr[j][0] <= i) {
						if(pr[j][1] != 0) {
							pr[j][3]++;
								if(j != selected_pr)
									pr[j][2]++;
						}
						else if(j == selected_pr)
							pr[j][3]++;
					}
				}
				
				if(i != 0) {
					if(selected_pr != t_ch[i - 1]) {       
						System.out.print("--" + i + "--P" + selected_pr);
					}
				}
				else
					System.out.print(i + "--P" + selected_pr);
				if(i == total_time - 1)
					System.out.print(" " + (i + 1));
				
			}
				System.out.println();
     
				System.out.println("P\t KP \t KQ ");
				
				for(int i = 1; i <= n; i++) {
					System.out.printf("%d\t%2dms\t%2dms",i ,pr[i][2] ,pr[i][3]);
					System.out.println();
				}
     
				System.out.println();
     
				float KMP = 0,KMQ = 0;
				for(int i = 1; i <= n; i++){
					KMP += pr[i][2];
					KMQ += pr[i][3];
				}
				KMP /= n;
				KMQ /= n;
				System.out.println("Koha Mesatare e Pritjes eshte: " + KMP + "ms");
				System.out.println("Koha Mesatare e Qendrimit eshte: " + KMQ + "ms");
			}
    
	}