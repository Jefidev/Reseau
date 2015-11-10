package serveur_chat_pfm;


public interface SourceTaches
{
    public Runnable getTache() throws InterruptedException;
    public boolean existTaches();
    public void recordTache(Runnable r);
}
