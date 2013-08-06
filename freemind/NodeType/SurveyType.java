package freemind.NodeType;

public class SurveyType extends NodeType{

	SurveyType type;

	private SurveyType getInstance(){
		if(type == null)
			type = new SurveyType();
		return type;
	}
	
	private SurveyType() {
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
	}

	@Override
	public NodeType getNodeType() {
		return getInstance();
	}	

}
