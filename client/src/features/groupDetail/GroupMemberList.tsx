import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';
import './GroupMemberList.scss';

const GroupMemberList = () => {
	const dummyMembers = Array(12).fill('닉네임');
	return (
		<div className="group-member body2">
			<div className="group-member-title body1">그룹 멤버</div>
			<div className="group-member-grid">
				{dummyMembers.map((name, index) => (
					<div key={index} className="member-card flex-col-center">
						<div className="avatar" />
						<div className="nickname body3">{name}</div>
					</div>
				))}
			</div>
		</div>
	);
};

export default GroupMemberList;
