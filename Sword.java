public class Sword extends Thing{
    private final String state;


    public Sword(){

        this.state = "Sword is shimmering.";

    }
    public Sword(String state){

        this.state = state;

    }


    public Sword tick(){

        if(state == "Sword is shimmering."){

            return new Sword("Sword is shimmering.");
        }

        return null;
    }



    @Override
    public String toString(){
        return this.state;

    }
}
