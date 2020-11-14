public class Candle extends Thing{
        private final String state ;


        public Candle(){

        this.state = "Candle flickers.";

        }
        public Candle(String state){

            this.state = state;

        }


        public Candle tick(){

            if(state == "Candle flickers."){

                return new Candle("Candle is getting shorter.");
            }
            else if(state == "Candle is getting shorter."){

                return new Candle("Candle is about to burn out.");
            }
            else if(state == "Candle is about to burn out."){

                return new Candle("Candle has burned out.");

            }
            else if(state == "Candle has burned out."){

                return new Candle("Candle has burned out.");

            }
            return null;
        }



        @Override
        public String toString(){
            return this.state;

        }


    }



