import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';
import Header from 'features/header/Header';
import GroupMenu from 'features/groupDetail/GroupMenu';
import GroupMemberList from 'features/groupDetail/GroupMemberList';
import GroupChecklist from 'features/groupDetail/GroupChecklist';
import NavBar from 'components/NavBar';

const GroupPage = () => {
	return (
		<div>
			<Header title="그룹명" showLogo={false} />
			<GroupMenu />
			<GroupMemberList />
			<GroupChecklist />
			<NavBar />
		</div>
	);
};

export default GroupPage;
