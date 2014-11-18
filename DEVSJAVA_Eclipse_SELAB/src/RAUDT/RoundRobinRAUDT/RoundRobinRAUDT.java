package RAUDT.RoundRobinRAUDT;
import java.awt.*;
import simView.*;

public class RoundRobinRAUDT extends ViewableDigraph
{
	public RoundRobinRAUDT()
	{
		super("RoundRobinRAUDT");
		
		ViewableAtomic[] user = new User [Constants.USER_NUM];
		ViewableAtomic[] vm = new VM [Constants.VM_NUM];
		ViewableAtomic jc = new JobClassifier("Job Classifier", 0, Constants.CLASSIFIER_SEED);
		ViewableAtomic ja = new JobAllocator("Job Allocator", 1, Constants.VM_NUM);
		
		add(jc);
		add(ja);
		addCoupling(jc, "out", ja, "in");
		
		for (int i = 0; i < Constants.USER_NUM; i++)
		{
			user[i] = new User("User #" + i, Constants.USER_INT_ARR_TIME, Constants.JOB_COUNT, Constants.USER_SEED[i]);
			add(user[i]);
			addCoupling(user[i], "out", jc, "in");
		}
		
		for (int i = 0; i < Constants.VM_NUM; i++)
		{
			vm[i] = new VM("VM #" + i, 0, Constants.VM_INFO[i]);
			add(vm[i]);
			addCoupling(ja, "out" + i, vm[i], "in");
			addCoupling(vm[i], "out", jc, "done");
			addCoupling(vm[i], "done", ja, "done");
			addCoupling(vm[i], "vm_info", ja, "vm_info");
		}
	}

    
    /**
     * Automatically generated by the SimView program.
     * Do not edit this manually, as such changes will get overwritten.
     */
    public void layoutForSimView()
    {
        preferredSize = new Dimension(1201, 733);
        ((ViewableComponent)withName("Job Classifier")).setPreferredLocation(new Point(218, 276));
        ((ViewableComponent)withName("User #0")).setPreferredLocation(new Point(50, 69));
        ((ViewableComponent)withName("User #2")).setPreferredLocation(new Point(368, 67));
        ((ViewableComponent)withName("VM #5")).setPreferredLocation(new Point(985, 627));
        ((ViewableComponent)withName("User #4")).setPreferredLocation(new Point(687, 64));
        ((ViewableComponent)withName("VM #2")).setPreferredLocation(new Point(386, 626));
        ((ViewableComponent)withName("VM #4")).setPreferredLocation(new Point(791, 627));
        ((ViewableComponent)withName("User #1")).setPreferredLocation(new Point(206, 68));
        ((ViewableComponent)withName("VM #1")).setPreferredLocation(new Point(198, 623));
        ((ViewableComponent)withName("User #3")).setPreferredLocation(new Point(524, 65));
        ((ViewableComponent)withName("VM #3")).setPreferredLocation(new Point(592, 627));
        ((ViewableComponent)withName("VM #0")).setPreferredLocation(new Point(10, 620));
        ((ViewableComponent)withName("Job Allocator")).setPreferredLocation(new Point(699, 252));
    }
}
