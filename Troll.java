public class Troll extends Thing{
    private final String state ;
    private final Boolean killed = false;


    public Troll(){

        this.state = "Troll lurks in the shadows.";

    }
    public Troll(String state){

        this.state = state;

    }



    public Troll tick(){

        if(state == "Troll lurks in the shadows."){

            return new Troll("Troll is getting hungry.");
        }
        else if(state == "Troll is getting hungry."){

            return new Troll("Troll is VERY hungry.");
        }
        else if(state == "Troll is VERY hungry."){

            return new Troll("Troll is SUPER HUNGRY and is about to ATTACK!");

        }
        else if(state == "Troll is SUPER HUNGRY and is about to ATTACK!"){

            return new Troll("Troll attacks!");

        }
        else if(state == "Troll attacks!"){

            return new Troll("Troll attacks!");

        }
        return null;
    }



    @Override
    public String toString(){
        return this.state;

    }


}

