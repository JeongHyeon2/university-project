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
             System.out.println("Shape ������");
        }
        public void finalize()        {
             System.out.println("Shape �Ҹ���");
        }    
   }

    class Rectangle extends Shape    {
        private int width, height;
        public Rectangle()        {
           System.out.println("Rectangle ������");
        }
        public void finalize()
        {
           System.out.println("Rectangle �Ҹ���");
        }
    }


