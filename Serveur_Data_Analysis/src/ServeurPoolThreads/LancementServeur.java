package ServeurPoolThreads;


public class LancementServeur
{
    public static void main(String[] args)
    {
        ServeurDataAnalysis sb = new ServeurDataAnalysis(31042, new ListeTaches(), 5);  // A AMELIORER => fichier properties
        sb.start();
    }    
}
