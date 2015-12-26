package serveur_compta;


public class ThreadTraitement extends Thread
{
    private final SourceTaches tachesAExecuter;
    
    private Runnable tacheEnCours;
    
    public ThreadTraitement(SourceTaches st)
    {
        tachesAExecuter = st;
    }
    
    @Override
    public void run()
    {
        while(!isInterrupted())
        {
            try
            {
                tacheEnCours = tachesAExecuter.getTache();
            }
            catch(InterruptedException e)
            {
                System.err.println("ThreadTraitement : Erreur de recuperation de la tache : " + e);
            }
            
            System.out.println("ThreadTraitement : Lancement du run de la tacheEnCours");
            tacheEnCours.run();
            System.out.println("fin de tâche");
        }
    }
}
