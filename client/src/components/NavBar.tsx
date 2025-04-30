import { useState } from 'react';
import { Home, Plus, User } from 'lucide-react';
import { useNavigate, useLocation } from 'react-router-dom';
import 'assets/style/NavBar.scss';
import StudyGroupForm from 'features/StudyGroupForm';

const NavBar = () => {
	const navigate = useNavigate();
	const location = useLocation();
	const [isStudyGroupFormOpen, setIsStudyGroupFormOpen] = useState(false);

	const isActive = (path: string) => location.pathname === path;

	const openStudyGroupForm = () => {
		setIsStudyGroupFormOpen(true);
	};

	const closeStudyGroupForm = () => {
		setIsStudyGroupFormOpen(false);
	};

	return (
		<>
			<nav className="nav-bar">
				<button
					onClick={() => navigate('/')}
					className={isActive('/') ? 'active' : ''}
				>
					<Home size={24} />
				</button>
				<button onClick={openStudyGroupForm} className="plus-button">
					<Plus size={36} />
				</button>
				<button
					onClick={() => navigate('/mypage')}
					className={isActive('/mypage') ? 'active' : ''}
				>
					<User size={24} />
				</button>
			</nav>

			{/* 모달 */}
			{isStudyGroupFormOpen && (
				<div
					style={{
						position: 'fixed',
						top: 0,
						left: 0,
						width: '100%',
						height: '100%',
						backgroundColor: 'rgba(0,0,0,0.5)',
						display: 'flex',
						justifyContent: 'center',
						alignItems: 'center',
						zIndex: 1500,
					}}
				>
					<div
						style={{
							background: 'white',
							borderRadius: '16px',
							padding: '32px',
							width: '400px',
							position: 'relative',
							boxShadow: '0 10px 25px rgba(0,0,0,0.3)',
						}}
					>
						<button
							onClick={closeStudyGroupForm}
							style={{
								position: 'absolute',
								top: '16px',
								right: '16px',
								fontSize: '24px',
								background: 'none',
								border: 'none',
								cursor: 'pointer',
							}}
						>
							✕
						</button>
						<StudyGroupForm />
					</div>
				</div>
			)}
		</>
	);
};

export default NavBar;
