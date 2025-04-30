import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';
import './GroupMemberList.scss';

const GroupMemberList = () => {
	const dummyMembers = Array(12).fill('닉네임');
	return (
		<div className="group-member">
			<div className="group-member-title">그룹 멤버</div>
			<div className="group-member-list">
				{dummyMembers.map((name, index) => (
					<div key={index} className="member-card">
						<div className="avatar" />
						<div className="nickname">{name}</div>
					</div>
				))}
			</div>
		</div>
	);
};

export default GroupMemberList;
