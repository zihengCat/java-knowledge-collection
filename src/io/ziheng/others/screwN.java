import java.util.Arrays;
import java.util.Scanner;

public class screwN {
	public static void main(String[] args) {
		Scanner sca1=new Scanner(System.in);
		System.out.println("请输入m:");
		int m=sca1.nextInt();
		Scanner sca2=new Scanner(System.in);
		System.out.println("请输入n:");
		int n=sca2.nextInt();
		int[][]scerw=new int[m][n];
		//控制运动方向（4个运动状态）
		int index=0;
		//控制行
		int i=0;
		//控制列
		int j=0;
		//数值
		int num=1;
		while (zero(scerw)) {
			//如果index等于3后，下一个状态应该是0
			index=(index==4)?0:index;
			//状态0：上（从左往右）
			if (index==0) {
				scerw[i][j]=num++;
				j++;
				if (j>=n) {
					j--;
					index++;
					
				}else {
					if (scerw[i][j]!=0) {
						index++;
						j--;
					}
					
				}
				
				
			}
			//状态1：右（从上往下）
			if (index==1) {
				scerw[i+1][j]=num++;
				i++;
				if ((i+1)>=m) {
					i--;
					index++;
					
				}else {
					if (scerw[i+1][j]!=0) {
						index++;
						i--;
					}
				}
				
			}
			//状态2：下（从右往左）
           if (index==2) {
        	   scerw[i+1][j-1]=num++;
        	   j--;
        	   if (j-1<0) {
        		   j++;
        		   index++;
				
			}else {
				if (scerw[i+1][j-1]!=0) {
					index++;
					j++;
				}
				
			}
        	   
			}
         //状态3：左（从下往上）
			if (index==3) {
				scerw[i][j-1]=num++;
				i--;
				if (i<=0) {
					i++;
					index++;
					
				} else {
					if (scerw[i][j-1]!=0) {
						index++;
						i++;
					}
				}
				
			}
		}
		
		for (int i1 = 0; i1 < scerw.length; i1++) {
			for (int k = 0; k < scerw.length; k++) {
				System.out.print(scerw[i1][k]+"\t");
			}System.out.println(" ");
			
		}
		
	}
	//构造一个判断是否填满非零的方法
	public static boolean zero(int[][] ss) {
		for (int i = 0; i < ss.length; i++) {
			for (int j = 0; j < ss[0].length; j++) {
				if (ss[i][j]==0) {
					return true;
				}
			}
		}
		return false;
	}
}