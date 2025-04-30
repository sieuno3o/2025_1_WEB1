import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';
import './GroupMenu.scss';

const GroupMenu = () => {
	return (
		<div className="group-menu flex-row-between">
			<div className="menu-item flex-col-center">
				<img src="/assets/calendar-icon.png" />
				<div>출석체크</div>
			</div>
			<div className="menu-item flex-col-center">
				<img src="/assets/notice-icon.png" />
				<div>공지</div>
			</div>
			<div className="menu-item flex-col-center">
				<img src="/assets/ranking-icon.png" />
				<div>랭킹</div>
			</div>
		</div>
	);
};

export default GroupMenu;
