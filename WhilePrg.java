//Prg for while loop
class WhilePrg
{
	int num1;
	public void show()
	{
	num1=10;
	while(num1<10)
	{
	System.out.print(num1+"\t");
	++num1;
	}
	}
	public static void main(String a[])
	{
	WhilePrg p1=new WhilePrg();
	p1.show();
	}
}