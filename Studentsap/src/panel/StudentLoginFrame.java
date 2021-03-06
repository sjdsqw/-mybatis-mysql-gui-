package panel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Bean.Student;
import BeanDao.Studentimpl;
import util.SqlSessionUtil;

public class StudentLoginFrame extends JFrame {
	JFrame jf = this;
	JLabel jl1 = new JLabel("用户名", JLabel.CENTER);
	JLabel jl2 = new JLabel("密码", JLabel.CENTER);
	JTextField jt1 = new JTextField(20);
	JTextField jt2 = new JTextField(20);
	JButton jb1 = new JButton("提交");
	JButton jb2 = new JButton("返回");

	public StudentLoginFrame() {
		// TODO Auto-generated constructor stub

		// 设置窗体
		this.setLocation(380, 60);
		this.setSize(580, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(9, 1));

		// 头部字
		this.add(new JPanel() {
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);

				g.setFont(new Font("黑体", Font.BOLD, 30));
				g.drawString("学生登录", 170, 40);

			}

		});

		// 各种框
		JPanel jp1 = new JPanel(new GridLayout(1, 2));
		JPanel jp2 = new JPanel(new GridLayout(1, 2));
		JPanel jp3 = new JPanel(new GridLayout(1, 2));
		// kong jpanel
		JPanel jp4 = new JPanel(new GridLayout(1, 1));
		JPanel jp5 = new JPanel(new GridLayout(1, 1));
		JPanel jp6 = new JPanel(new GridLayout(1, 1));
		JPanel jp7 = new JPanel(new GridLayout(1, 1));
		JPanel jp8 = new JPanel(new GridLayout(1, 1));
		JPanel jp9 = new JPanel(new GridLayout(1, 1));

		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		jp2.add(jt2);
		jp3.add(jb1);
		jp3.add(jb2);
		this.add(jp8);
		this.add(jp9);

		this.add(jp1);

		this.add(jp4);
		this.add(jp5);

		this.add(jp2);

		this.add(jp6);

		this.add(jp3);

		// 提交事件
		jb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);

				String userid = jt1.getText();
				String password = jt2.getText();
				if(userid.trim()=="")
				{
					JOptionPane.showMessageDialog(null, "请输入用户名", "提示", JOptionPane.WARNING_MESSAGE);
				   return;
				}
				if(password.trim()=="")
				{
					JOptionPane.showMessageDialog(null, "请输入密码", "提示", JOptionPane.WARNING_MESSAGE);
				   return;
				}
				
				
				Student stu = null;
				try {
					Studentimpl sim = new Studentimpl(SqlSessionUtil.getSqlSession());
					Student sel = sim.selstudentbyid(userid);
					if (sel == null) {
						JOptionPane.showMessageDialog(null, "未找到此用户", "提示", JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (password.trim().equals(sel.getPassword())) {
						sel.setId(userid);
						stu = sel;
					} else {
						JOptionPane.showMessageDialog(null, "密碼錯誤", "提示", JOptionPane.WARNING_MESSAGE);
						return;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "未找到", "提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				// 查找

				if (stu != null) {
					jf.dispose();
					new Studentmain(stu);
					return;

				} else {
					JOptionPane.showMessageDialog(null, "异常错误", "提示", JOptionPane.WARNING_MESSAGE);
				}
			}

		});

		// 返回事件
		jb2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				jf.dispose();
				new MainFrame();

			}
		});

	}

}
