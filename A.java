class A extends Thread 
{
    public void run()
    {
        for(int i=0;i<5;i++)
        {
            System.out.println("From Thread A : "+i);
        }
        System.out.println("Exiting from A");
    }
}
class B extends Thread
{
    public void run()
    {
        for(int i=1;i<5;i++)
        {
            System.out.println("From Thread B : "+i);
        }
        System.out.println("Exiting from B");
    }
}

class ThreadEx 
{
    public static void main(String args[])
    {
        new A().start();
        new B().start();
    }
}
