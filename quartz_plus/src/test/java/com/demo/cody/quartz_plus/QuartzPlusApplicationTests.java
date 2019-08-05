package com.demo.cody.quartz_plus;

import com.demo.cody.QuartzPlusApplication;
import com.demo.cody.config.CronTaskRegistrar;
import com.demo.cody.schedul.SchedulingRunnable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = QuartzPlusApplication.class)
public class QuartzPlusApplicationTests {

	@Autowired
	CronTaskRegistrar cronTaskRegistrar;

	@Test
	public void testTask() throws InterruptedException
	{
		SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskNoParams", null);
		cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");

		// 便于观察
		Thread.sleep(3000000);
	}

	@Test
	public void testHaveParamsTask() throws InterruptedException
	{
		SchedulingRunnable task = new SchedulingRunnable("DemoTask", "taskWithParams", "haha", 23);
		cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");

		// 便于观察
		Thread.sleep(3000000);
	}
}
