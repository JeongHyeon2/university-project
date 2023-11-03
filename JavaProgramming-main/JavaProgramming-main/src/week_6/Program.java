package week_6;
  public class Program     {
        static void main(String args[])         {
            Rectangle r;

            r = new Rectangle();
        }     
   }

    class Shape    {
        private int x, y;
        public Shape()        {
             System.out.println("Shape 持失切");
        }
        public void finalize()        {
             System.out.println("Shape 社瑚切");
        }    
   }

    class Rectangle extends Shape    {
        private int width, height;
        public Rectangle()        {
           System.out.println("Rectangle 持失切");
        }
        public void finalize()
        {
           System.out.println("Rectangle 社瑚切");
        }
    }


