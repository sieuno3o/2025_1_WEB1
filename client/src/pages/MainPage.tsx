import { useNavigate } from 'react-router-dom';
import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';
import StudyGroupsList from 'features/studyGroupList/StudyGroupsList';

const MainPage = () => {
	const navigate = useNavigate();
	const isLoggedIn = false;

	const goToLogin = () => {
		navigate('/login');
	};

	return (
		<div className="">
			<div>
				{/* 헤더 컴포넌트 */}
				{/* 검색 컴포넌트 */}
				<StudyGroupsList />
				{/* 하단바 컴포넌트 */}

				{!isLoggedIn && <button onClick={goToLogin}>로그인</button>}
				{isLoggedIn && <p>환영합니다! 😄</p>}
			</div>
		</div>
	);
};

export default MainPage;
