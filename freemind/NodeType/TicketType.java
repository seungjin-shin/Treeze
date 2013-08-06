package freemind.NodeType;

public class TicketType extends NodeType{
	TicketType type;

	private TicketType getInstance(){
		if(type == null)
			type = new TicketType();
		return type;
	}
	
	private TicketType() {
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
	}

	@Override
	public NodeType getNodeType() {
		// TODO Auto-generated method stub
		return getInstance();
	}

	@Override
	public void forSurveyAct() {
		// TODO Auto-generated method stub
		
	}

}
