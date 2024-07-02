package com.example.usersgithub.ui.detail


//class FollowersFragment : Fragment() {
//
//    private lateinit var binding: FragmentFollowersBinding
//    private val viewModel by viewModels<DetailViewModel> {
//        ViewModelFactory.getInstance(requireActivity().application)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val username = requireActivity().intent.extras?.getString("login").toString()
//        binding = FragmentFollowersBinding.bind(view)
//
//        binding.rvFollowers.layoutManager = LinearLayoutManager(requireActivity())
//        setupData(username)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentFollowersBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    private fun setupData(username: String) {
//        viewModel.getFollowers(username).observe(viewLifecycleOwner) {
//            when (it) {
//                is Result.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                }
//
//                is Result.Success -> {
//                    binding.progressBar.visibility = View.GONE
//                    val adapter = FollowAdapter()
//                    adapter.submitList(it.data)
//                    binding.rvFollowers.adapter = adapter
//                }
//
//                is Result.Error -> {
//                    binding.progressBar.visibility = View.GONE
//                }
//            }
//        }
//    }
//}