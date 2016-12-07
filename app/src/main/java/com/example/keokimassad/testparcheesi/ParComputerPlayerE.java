package com.example.keokimassad.testparcheesi;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Random;

import game.GameComputerPlayer;
import game.infoMsg.GameInfo;


/**
 * Created by KeokiMassad on 11/20/16.
 */

public class ParComputerPlayerE extends GameComputerPlayer {

    ParLocalGame parLocalGame = new ParLocalGame();
    int playerNumber = playerNum;
    private int[] location = new int[4];
    ParRollAction rollAction;
    ParSelectAction selectAction;
    ParUseDieAction useDieAction;
    ParMoveAction moveAction;

    public ParComputerPlayerE(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if(parLocalGame.canMove(playerNumber))
        {
            if(info instanceof ParState)
            {
                // roll the die
                rollAction = new ParRollAction(this);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        game.sendAction(rollAction);
                    }
                }, 2000);

                Random rand = new Random();
                do {
                    // choose a random pawn
                    int randSelect = rand.nextInt(4);
                    selectAction = new ParSelectAction(this, randSelect);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            game.sendAction(selectAction);
                        }
                    }, 2000);

                    // choose a random die
                    int randDie = rand.nextInt(3);
                    switch (randDie) {
                        case 0:
                            useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val(), randDie);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    game.sendAction(selectAction);
                                }
                            }, 2000);
                            break;
                        case 1:
                            useDieAction = new ParUseDieAction(this, ((ParState) info).getDice2Val(), randDie);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    game.sendAction(selectAction);
                                }
                            }, 2000);
                            break;
                        case 2:
                            useDieAction = new ParUseDieAction(this, ((ParState) info).getDice1Val() + ((ParState) info).getDice2Val(), randDie);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    game.sendAction(selectAction);
                                }

                            },2000);
                            break;
                    }


                    moveAction = new ParMoveAction(this);
                } while(((ParState) info).getEmptySet() == false);




//               if (//4 objects are at home)
//                        )
//                {
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //have one dice be three and one dice be 2.
//                            //bring one piece out.
//                        }
//                    }, 2000);
//
//                }
//                else if(//3 objects are at home and first object is on first square )
//                        )
//                {
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //have one dice roll 3 and one dice roll 1.
//                            //advance that one object four spots.
//                        }
//                    }, 2000);
//                }
//                else if(//3 objects are at home and first object is not on first square)
//                        )
//                {
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //have one dice roll 3 and one dice roll 2.
//                            //bring one object out.
//                        }
//                    }, 2000);
//                }
//                else if(//2 objects are at home and object is on first square)
//                        )
//                {
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //have one dice roll 2 and one dice roll 1.
//                            //advance that one object three spots.
//                        }
//                    }, 2000);
//                }
//                else if(//2 objects are at home and no objects on first square )
//                        )
//                {
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //have one dice roll 3 and one dice roll 2.
//                            //bring one object out.
//                        }
//                    }, 2000);
//                }
//                else if(//1 object is at home and 1 object on first square)
//                        )
//                {
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //have one dice roll 1 and one dice roll 1.
//                            //advance object 2 spots.
//                        }
//                    }, 2000);
//                }
//                else if(//1 object is at home and 0 objects on first square)
//                        )
//                {
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //have one dice roll 3 and one dice roll 2.
//                            //advance last object out of home spot.
//                        }
//                    }, 2000);
//                }
//                else if(//0 objects at home and pawn 1 can move one forward and pawn 2 can move one forward)
//                        )
//                {
//                    Random ran = new Random();
//                    int rand = ran.nextInt(4);
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //roll two 1's
//                            // advance each of the first two pieces one spot
//                        }
//                    }, 2000);
//
//                }
//                else if(//0 objects at home and pawn 1 can move one forward and pawn 2 cannot move one forward)
//                        )
//                {
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //roll two 1's
//                            // advance first piece forward and advance third piece one spot
//                        }
//                    }, 2000);
//                }
            }
        }
        else
        {
            return;
        }
    }

}