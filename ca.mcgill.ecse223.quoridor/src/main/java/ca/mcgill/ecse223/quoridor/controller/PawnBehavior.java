/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.quoridor.controller;
import ca.mcgill.ecse223.quoridor.QuoridorApplication;
import ca.mcgill.ecse223.quoridor.model.*;

// line 5 "../../../../../StateMachine.ump"
public class PawnBehavior
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum MoveDirection { East, South, West, North }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PawnBehavior State Machines
  public enum PawnSM { Playing, Finished }
  public enum PawnSMPlayingNorthSouth { Null, NorthSouth }
  public enum PawnSMPlayingNorthSouthNorthSouth { Null, Setup, SouthEdge, SouthBorder, MiddleNS, NorthBorder, NorthEdge }
  public enum PawnSMPlayingEastWest { Null, EastWest }
  public enum PawnSMPlayingEastWestEastWest { Null, Setup, WestEdge, WestBorder, MiddleEW, EastBorder, EastEdge }
  private PawnSM pawnSM;
  private PawnSMPlayingNorthSouth pawnSMPlayingNorthSouth;
  private PawnSMPlayingNorthSouthNorthSouth pawnSMPlayingNorthSouthNorthSouth;
  private PawnSMPlayingEastWest pawnSMPlayingEastWest;
  private PawnSMPlayingEastWestEastWest pawnSMPlayingEastWestEastWest;

  //PawnBehavior Associations
  private Game currentGame;
  private Player player;
  
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PawnBehavior()
  {
    setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth.Null);
    setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
    setPawnSMPlayingEastWest(PawnSMPlayingEastWest.Null);
    setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
    setPawnSM(PawnSM.Playing);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getPawnSMFullName()
  {
    String answer = pawnSM.toString();
    if (pawnSMPlayingNorthSouth != PawnSMPlayingNorthSouth.Null) { answer += "." + pawnSMPlayingNorthSouth.toString(); }
    if (pawnSMPlayingNorthSouthNorthSouth != PawnSMPlayingNorthSouthNorthSouth.Null) { answer += "." + pawnSMPlayingNorthSouthNorthSouth.toString(); }
    if (pawnSMPlayingEastWest != PawnSMPlayingEastWest.Null) { answer += "." + pawnSMPlayingEastWest.toString(); }
    if (pawnSMPlayingEastWestEastWest != PawnSMPlayingEastWestEastWest.Null) { answer += "." + pawnSMPlayingEastWestEastWest.toString(); }
    return answer;
  }

  public PawnSM getPawnSM()
  {
    return pawnSM;
  }

  public PawnSMPlayingNorthSouth getPawnSMPlayingNorthSouth()
  {
    return pawnSMPlayingNorthSouth;
  }

  public PawnSMPlayingNorthSouthNorthSouth getPawnSMPlayingNorthSouthNorthSouth()
  {
    return pawnSMPlayingNorthSouthNorthSouth;
  }

  public PawnSMPlayingEastWest getPawnSMPlayingEastWest()
  {
    return pawnSMPlayingEastWest;
  }

  public PawnSMPlayingEastWestEastWest getPawnSMPlayingEastWestEastWest()
  {
    return pawnSMPlayingEastWestEastWest;
  }

  public boolean finishGame()
  {
    boolean wasEventProcessed = false;
    
    PawnSM aPawnSM = pawnSM;
    switch (aPawnSM)
    {
      case Playing:
        exitPawnSM();
        setPawnSM(PawnSM.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startGame()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case Setup:
        if (getPlayer().getGameAsWhite.equals(getCurrentGame()))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    switch (aPawnSMPlayingEastWestEastWest)
    {
      case Setup:
        exitPawnSMPlayingEastWestEastWest();
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean stepUp()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case SouthEdge:
        if (isLegalMove(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      case SouthBorder:
        if (isLegalMove(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleNS:
        if (isLegalMove(MoveDirection.North)&&getCurrentPawnRow()==3)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalMove(MoveDirection.North)&&getCurrentPawnRow()>=4&&getCurrentPawnRow()<=7)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthBorder:
        if (isLegalMove(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean jumpUp()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case SouthEdge:
        if (isLegalJump(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case SouthBorder:
        if (isLegalJump(MoveDirection.North))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleNS:
        if (isLegalJump(MoveDirection.North)&&getCurrentPawnRow()==3)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthEdge);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.North)&&getCurrentPawnRow()==4)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.North)&&getCurrentPawnRow()>=5&&getCurrentPawnRow()<=7)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean stepDown()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case SouthBorder:
        if (isLegalMove(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleNS:
        if (isLegalMove(MoveDirection.South)&&getCurrentPawnRow()==7)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalMove(MoveDirection.South)&&getCurrentPawnRow()>=3&&getCurrentPawnRow()<=6)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthBorder:
        if (isLegalMove(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthEdge:
        if (isLegalMove(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.NorthBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean jumpDown()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth = pawnSMPlayingNorthSouthNorthSouth;
    switch (aPawnSMPlayingNorthSouthNorthSouth)
    {
      case MiddleNS:
        if (isLegalJump(MoveDirection.South)&&getCurrentPawnRow()==7)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthEdge);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.South)&&getCurrentPawnRow()==6)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.SouthBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.South)&&getCurrentPawnRow()>=3&&getCurrentPawnRow()<=5)
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthBorder:
        if (isLegalJump(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      case NorthEdge:
        if (isLegalJump(MoveDirection.South))
        {
          exitPawnSMPlayingNorthSouthNorthSouth();
          setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.MiddleNS);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean stepRight()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingEastWestEastWest)
    {
      case WestEdge:
        if (isLegalMove(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      case WestBorder:
        if (isLegalMove(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleEW:
        if (isLegalMove(MoveDirection.East)&&getCurrentPawnColumn()==7)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalMove(MoveDirection.East)&&getCurrentPawnColumn()>=3&&Column()<=6)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastBorder:
        if (isLegalMove(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean jumpRight()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingEastWestEastWest)
    {
      case WestEdge:
        if (isLegalJump(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case WestBorder:
        if (isLegalJump(MoveDirection.East))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleEW:
        if (getCurrentPawnColumn()>=3&&getCurrentPawnColumn()<=5)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.East)&&getCurrentPawnColumn()==7)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastEdge);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.East)&&getCurrentPawnColumn()==6)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean stepLeft()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingEastWestEastWest)
    {
      case WestBorder:
        if (isLegalMove(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestEdge);
          wasEventProcessed = true;
          break;
        }
        break;
      case MiddleEW:
        if (isLegalMove(MoveDirection.West)&&getCurrentPawnColumn()==3)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalMove(MoveDirection.West)&&getCurrentPawnColumn()>=4&&getCurrentPawnColumn()<=7)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastBorder:
        if (isLegalMove(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastEdge:
        if (isLegalMove(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.EastBorder);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean jumpLeft()
  {
    boolean wasEventProcessed = false;
    
    PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest = pawnSMPlayingEastWestEastWest;
    switch (aPawnSMPlayingEastWestEastWest)
    {
      case MiddleEW:
        if (isLegalJump(MoveDirection.West)&&getCurrentPawnColumn()==3)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestEdge);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.West)&&getCurrentPawnColumn()==4)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.WestBorder);
          wasEventProcessed = true;
          break;
        }
        if (isLegalJump(MoveDirection.West)&&isLegalJump(MoveDirection.West)&&getCurrentPawnColumn()>=5&&getCurrentPawnColumn()<=7)
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastBorder:
        if (isLegalJump(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      case EastEdge:
        if (isLegalJump(MoveDirection.West))
        {
          exitPawnSMPlayingEastWestEastWest();
          setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.MiddleEW);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitPawnSM()
  {
    switch(pawnSM)
    {
      case Playing:
        exitPawnSMPlayingNorthSouth();
        exitPawnSMPlayingEastWest();
        break;
    }
  }

  private void setPawnSM(PawnSM aPawnSM)
  {
    pawnSM = aPawnSM;

    // entry actions and do activities
    switch(pawnSM)
    {
      case Playing:
        if (pawnSMPlayingNorthSouth == PawnSMPlayingNorthSouth.Null) { setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth.NorthSouth); }
        if (pawnSMPlayingEastWest == PawnSMPlayingEastWest.Null) { setPawnSMPlayingEastWest(PawnSMPlayingEastWest.EastWest); }
        break;
    }
  }

  private void exitPawnSMPlayingNorthSouth()
  {
    switch(pawnSMPlayingNorthSouth)
    {
      case NorthSouth:
        exitPawnSMPlayingNorthSouthNorthSouth();
        setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth.Null);
        break;
    }
  }

  private void setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth aPawnSMPlayingNorthSouth)
  {
    pawnSMPlayingNorthSouth = aPawnSMPlayingNorthSouth;
    if (pawnSM != PawnSM.Playing && aPawnSMPlayingNorthSouth != PawnSMPlayingNorthSouth.Null) { setPawnSM(PawnSM.Playing); }

    // entry actions and do activities
    switch(pawnSMPlayingNorthSouth)
    {
      case NorthSouth:
        if (pawnSMPlayingNorthSouthNorthSouth == PawnSMPlayingNorthSouthNorthSouth.Null) { setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Setup); }
        break;
    }
  }

  private void exitPawnSMPlayingNorthSouthNorthSouth()
  {
    switch(pawnSMPlayingNorthSouthNorthSouth)
    {
      case Setup:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case SouthEdge:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case SouthBorder:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case MiddleNS:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case NorthBorder:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
      case NorthEdge:
        setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth.Null);
        break;
    }
  }

  private void setPawnSMPlayingNorthSouthNorthSouth(PawnSMPlayingNorthSouthNorthSouth aPawnSMPlayingNorthSouthNorthSouth)
  {
    pawnSMPlayingNorthSouthNorthSouth = aPawnSMPlayingNorthSouthNorthSouth;
    if (pawnSMPlayingNorthSouth != PawnSMPlayingNorthSouth.NorthSouth && aPawnSMPlayingNorthSouthNorthSouth != PawnSMPlayingNorthSouthNorthSouth.Null) { setPawnSMPlayingNorthSouth(PawnSMPlayingNorthSouth.NorthSouth); }
  }

  private void exitPawnSMPlayingEastWest()
  {
    switch(pawnSMPlayingEastWest)
    {
      case EastWest:
        exitPawnSMPlayingEastWestEastWest();
        setPawnSMPlayingEastWest(PawnSMPlayingEastWest.Null);
        break;
    }
  }

  private void setPawnSMPlayingEastWest(PawnSMPlayingEastWest aPawnSMPlayingEastWest)
  {
    pawnSMPlayingEastWest = aPawnSMPlayingEastWest;
    if (pawnSM != PawnSM.Playing && aPawnSMPlayingEastWest != PawnSMPlayingEastWest.Null) { setPawnSM(PawnSM.Playing); }

    // entry actions and do activities
    switch(pawnSMPlayingEastWest)
    {
      case EastWest:
        if (pawnSMPlayingEastWestEastWest == PawnSMPlayingEastWestEastWest.Null) { setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Setup); }
        break;
    }
  }

  private void exitPawnSMPlayingEastWestEastWest()
  {
    switch(pawnSMPlayingEastWestEastWest)
    {
      case Setup:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case WestEdge:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case WestBorder:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case MiddleEW:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case EastBorder:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
      case EastEdge:
        setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest.Null);
        break;
    }
  }

  private void setPawnSMPlayingEastWestEastWest(PawnSMPlayingEastWestEastWest aPawnSMPlayingEastWestEastWest)
  {
    pawnSMPlayingEastWestEastWest = aPawnSMPlayingEastWestEastWest;
    if (pawnSMPlayingEastWest != PawnSMPlayingEastWest.EastWest && aPawnSMPlayingEastWestEastWest != PawnSMPlayingEastWestEastWest.Null) { setPawnSMPlayingEastWest(PawnSMPlayingEastWest.EastWest); }
  }
  /* Code from template association_GetOne */
  public Game getCurrentGame()
  {
    return currentGame;
  }

  public boolean hasCurrentGame()
  {
    boolean has = currentGame != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setCurrentGame(Game aNewCurrentGame)
  {
    boolean wasSet = false;
    currentGame = aNewCurrentGame;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setPlayer(Player aNewPlayer)
  {
    boolean wasSet = false;
    player = aNewPlayer;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    currentGame = null;
    player = null;
  }


  /**
   * Returns the current row number of the pawn
   */
  // line 116 "../../../../../StateMachine.ump"
  public int getCurrentPawnRow(){
	  currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
	  Player player = currentGame.getCurrentPosition().getPlayerToMove();
	  int row;
	  if (player.hasGameAsBlack()) {
		  row = currentGame.getCurrentPosition().getBlackPosition().getTile().getRow();
	  } else {
		  row = currentGame.getCurrentPosition().getWhitePosition().getTile().getRow();
	  }
    return row;
  }


  /**
   * Returns the current column number of the pawn
   */
  // line 118 "../../../../../StateMachine.ump"
  public int getCurrentPawnColumn(){
	  currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
	  Player player = currentGame.getCurrentPosition().getPlayerToMove();
	  int col;
	  if (player.hasGameAsBlack()) {
		  col = currentGame.getCurrentPosition().getBlackPosition().getTile().getColumn();
	  } else {
		  col = currentGame.getCurrentPosition().getWhitePosition().getTile().getColumn();
	  }
    return col;
  }


  /**
   * Returns if it is legal to step in the given direction
   */
  // line 120 "../../../../../StateMachine.ump"
  public boolean isLegalStep(MoveDirection dir){
	  currentGame = QuoridorApplication.getQuoridor().getCurrentGame();
	  Player player = currentGame.getCurrentPosition().getPlayerToMove();
	  Player opponent = player.getNextPlayer();
    return false;
  }


  /**
   * Returns if it is legal to jump in the given direction
   */
  // line 122 "../../../../../StateMachine.ump"
  public boolean isLegalJump(MoveDirection dir){
    return false;
  }


  /**
   * Action to be called when an illegal move is attempted
   */
  // line 125 "../../../../../StateMachine.ump"
  public void illegalMove(){
    
  }

}